import React from 'react';
import { AppLayout } from '@vaadin/react-components/AppLayout';
import { DrawerToggle } from '@vaadin/react-components/DrawerToggle';
import { NavLink } from 'react-router-dom';

export default function MainLayout() {
  return (
    <AppLayout primarySection="drawer">
      <DrawerToggle slot="navbar" />
      <h1 slot="navbar">SourBot DeFi Portfolio Manager</h1>
      <div slot="drawer">
        <NavLink to="/">Home</NavLink>
        <NavLink to="/defi-dashboard">DeFi Dashboard</NavLink>
        {/* Add other navigation links as needed */}
      </div>
      <div>
        {/* Router outlet will be rendered here */}
      </div>
    </AppLayout>
  );
}