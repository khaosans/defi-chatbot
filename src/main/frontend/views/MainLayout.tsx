import React from 'react';
import { AppLayout } from '@vaadin/react-components/AppLayout';
import { DrawerToggle } from '@vaadin/react-components/DrawerToggle';
import { NavLink } from 'react-router-dom';
import Header from "Frontend/components/Header";

export default function MainLayout({ children }: { children: React.ReactNode }) {
  return (
    <AppLayout primarySection="drawer">
      <Header/>
      {children}
    </AppLayout>
  );
}