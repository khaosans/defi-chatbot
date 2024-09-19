import { useEffect, useState } from "react";
import { BookingService, PortfolioService, AssistantService } from "Frontend/generated/endpoints";
import BookingDetails from "Frontend/generated/org/vaadin/marcus/service/BookingDetails";
import AccountDetails from "Frontend/generated/org/vaadin/marcus/service/AccountDetails";
import { MessageInput } from "@vaadin/react-components/MessageInput";
import { nanoid } from "nanoid";
import { MessageItem } from "../components/Message";
import MessageList from "Frontend/components/MessageList";
import "Frontend/themes/customer-support-agent/styles.css";
import Header from "Frontend/components/Header";
import ChatHistory from "Frontend/components/ChatHistory";

// Remove AssistantService from the component logic
// Replace it with a mock function for now
const mockAssistantService = {
  chat: (chatId: string, message: string) => ({
    onNext: (callback: (token: string) => void) => {
      callback("This is a mock response.");
      return { onError: (p0: () => void) => {}, onComplete: () => {} };
    },
  }),
};

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
  const [chatSessions, setChatSessions] = useState<{ id: string; title: string }[]>([
    { id: chatId, title: "Current Chat" },
  ]);
  const [theme, setTheme] = useState('light');

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
   
  };

  const handleSelectSession = (id: string) => {
    // TODO: Implement session switching logic
    console.log(`Switching to session ${id}`);
  };

  const toggleTheme = () => {
    const newTheme = theme === 'light' ? 'dark' : 'light';
    setTheme(newTheme);
    document.body.setAttribute('data-theme', newTheme);
  };

  // Apply theme on component mount
  useEffect(() => {
    document.body.setAttribute('data-theme', theme);
  }, []);

  if (isLoading) {
    return <div className="flex justify-center items-center h-full">Loading...</div>;
  }

  if (error) {
    return <div className="flex justify-center items-center h-full text-red-500">{error}</div>;
  }

  return (
    <div className="index-container">
      <div className="sidebar">
        <ChatHistory sessions={chatSessions} onSelectSession={handleSelectSession} />
      </div>
      <div className="main-content">
        <Header>
          <button onClick={toggleTheme} className="theme-toggle-button">
            {theme === 'light' ? 'Dark Mode' : 'Light Mode'}
          </button>
        </Header>
        <div className="message-container">
          <MessageList messages={messages} />
        </div>
        <div className="input-container">
          <MessageInput 
            onSubmit={e => sendMessage(e.detail.value)} 
            disabled={working}
          />
        </div>
      </div>
    </div>
  );
}
