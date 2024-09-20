import React from 'react';
import { AppLayout } from '@vaadin/react-components/AppLayout';
import Header from "Frontend/components/Header";

export default function MainLayout({ children }: { children: React.ReactNode }) {
  return (
    <AppLayout primarySection="drawer">
      <Header logoutUrl="/logout" /> {/* Pass the logoutUrl prop here */}
      {children}
    </AppLayout>
  );
}