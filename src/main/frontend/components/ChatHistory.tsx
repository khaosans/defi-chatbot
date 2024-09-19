import React from 'react';

interface ChatSession {
  id: string;
  title: string;
}

interface ChatHistoryProps {
  sessions: ChatSession[];
  onSelectSession: (id: string) => void;
}

const ChatHistory: React.FC<ChatHistoryProps> = ({ sessions, onSelectSession }) => {
  return (
    <div className="chat-history">
      <h2>Chat History</h2>
      <ul>
        {sessions.map((session) => (
          <li
            key={session.id}
            onClick={() => onSelectSession(session.id)}
          >
            {session.title}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ChatHistory;