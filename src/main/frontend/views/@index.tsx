import { useEffect, useState } from "react";
import { AssistantService, BookingService, PortfolioService } from "Frontend/generated/endpoints";
import BookingDetails from "Frontend/generated/org/vaadin/marcus/service/BookingDetails";
import AccountDetails from "Frontend/generated/org/vaadin/marcus/service/AccountDetails";
import { MessageInput } from "@vaadin/react-components/MessageInput";
import { nanoid } from "nanoid";
import { MessageItem } from "../components/Message";
import MessageList from "Frontend/components/MessageList";
import "Frontend/themes/customer-support-agent/styles.css";
import Header from "Frontend/components/Header";

export default function Index() {
  const [chatId] = useState(nanoid());
  const [working, setWorking] = useState(false);
  const [accounts, setAccounts] = useState<AccountDetails[]>([]);
  const [bookings, setBookings] = useState<BookingDetails[]>([]);
  const [messages, setMessages] = useState<MessageItem[]>([{
    role: 'assistant',
    content: 'Welcome to Funnair! How can I help you?'
  }]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    setIsLoading(true);
    setError(null);
    Promise.all([
      BookingService.getBookings(),
      PortfolioService.getAccounts()
    ])
      .then(([bookingsData, accountsData]) => {
        setBookings(bookingsData);
        setAccounts(accountsData);
      })
      .catch((err: Error) => setError("Failed to load data. Please try again."))
      .finally(() => setIsLoading(false));
  }, []);

  const addMessage = (message: MessageItem) => {
    setMessages(messages => [...messages, message]);
  };

  const appendToLatestMessage = (chunk: string) => {
    setMessages(messages => {
      const latestMessage = messages[messages.length - 1];
      latestMessage.content += chunk;
      return [...messages.slice(0, -1), latestMessage];
    });
  };

  const sendMessage = async (message: string) => {
    setWorking(true);
    addMessage({ role: 'user', content: message });
    let first = true;
    AssistantService.chat(chatId, message)
      .onNext((token: string) => {
        if (first && token) {
          addMessage({ role: 'assistant', content: token });
          first = false;
        } else {
          appendToLatestMessage(token);
        }
      })
      .onError(() => {
        setError("Failed to send message. Please try again.");
        setWorking(false);
      })
      .onComplete(() => setWorking(false));
  };

  if (isLoading) {
    return <div className="flex justify-center items-center h-full">Loading...</div>;
  }

  if (error) {
    return <div className="flex justify-center items-center h-full text-red-500">{error}</div>;
  }

  return (
    <div className="index-container">
      <Header />
      <div className="index-message-list">
        <MessageList messages={messages} />
      </div>
      <div className="index-message-input">
        <MessageInput 
          onSubmit={e => sendMessage(e.detail.value)} 
          disabled={working}
        />
      </div>
    </div>
  );
}
