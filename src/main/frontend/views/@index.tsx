import { useEffect, useState } from "react";
import { AssistantService, BookingService } from "Frontend/generated/endpoints";
import BookingDetails from "../generated/org/vaadin/marcus/service/BookingDetails";
import { GridColumn } from "@vaadin/react-components/GridColumn";
import { Grid } from "@vaadin/react-components/Grid";
import { MessageInput } from "@vaadin/react-components/MessageInput";
import { nanoid } from "nanoid";
import { SplitLayout } from "@vaadin/react-components/SplitLayout";
import Message, { MessageItem } from "../components/Message";
import MessageList from "Frontend/components/MessageList";
import MainLayout from "Frontend/views/MainLayout";

export default function Index() {
  const [chatId, setChatId] = useState(nanoid());
  const [working, setWorking] = useState(false);
  const [bookings, setBookings] = useState<BookingDetails[]>([]);
  const [messages, setMessages] = useState<MessageItem[]>([{
    role: 'assistant',
    content: 'Welcome to SourBot Labs?'
  }]);

  useEffect(() => {
    if (!working) {
      BookingService.getBookings().then(setBookings);
    }
  }, [working]);

  function addMessage(message: MessageItem) {
    setMessages(messages => [...messages, message]);
  }

  function appendToLatestMessage(chunk: string) {
    setMessages(messages => {
      const latestMessage = messages[messages.length - 1];
      latestMessage.content += chunk;
      return [...messages.slice(0, -1), latestMessage];
    });
  }

  async function sendMessage(message: string) {
    setWorking(true);
    addMessage({
      role: 'user',
      content: message
    });
    let first = true;
    AssistantService.chat(chatId, message)
        .onNext(token => {
          if (first && token) {
            addMessage({
              role: 'assistant',
              content: token
            });
            first = false;
          } else {
            appendToLatestMessage(token);
          }
        })
        .onError(() => setWorking(false))
        .onComplete(() => setWorking(false));
  }

  return (
      <MainLayout>
        <div className="index-container" >
          <div className="sidebar" style={{ width: '500px' }}>
            <h3>Chat History</h3>
            <div className="chat-history">
              <ul>
                {messages.map((message, index) => (
                    <li key={index}>
                      <strong>{message.role}:</strong> {message.content}
                    </li>
                ))}
              </ul>
            </div>
          </div>
          <div className="main-content relative h-32 w-32 ...">
              <MessageList messages={messages} className="index-message-list"/>
              <MessageInput onSubmit={e => sendMessage(e.detail.value)} className="index-message-input "/>
          </div>
        </div>
      </MainLayout>
  );
}