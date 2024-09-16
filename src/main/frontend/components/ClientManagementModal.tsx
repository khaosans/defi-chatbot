import { useState, useEffect } from 'react';
import { Dialog } from '@vaadin/react-components/Dialog.js';
import { Button } from '@vaadin/react-components/Button.js';
import { Grid } from '@vaadin/react-components/Grid.js';
import { GridColumn } from '@vaadin/react-components/GridColumn.js';
import { ClientProfileService } from 'Frontend/generated/endpoints';
import ClientProfile from 'Frontend/generated/org/vaadin/marcus/service/ClientProfile';

interface ClientManagementModalProps {
  open: boolean;
  onClose: () => void;
}

export default function ClientManagementModal({ open, onClose }: ClientManagementModalProps) {
  const [clients, setClients] = useState<ClientProfile[]>([]);

  useEffect(() => {
    if (open) {
      fetchClients();
    }
  }, [open]);

  const fetchClients = async () => {
    try {
      const fetchedClients = await ClientProfileService.getAllClients();
      setClients(fetchedClients);
    } catch (error) {
      console.error('Failed to fetch clients:', error);
    }
  };

  return (
    <Dialog 
      opened={open} 
      onOpenedChanged={(e) => !e.detail.value && onClose()} 
      header="Client Management"
      resizable
      draggable
    >
      <div className="p-4" style={{ width: '600px', height: '400px' }}>
        <h3 className="text-lg font-semibold mb-4">Client List</h3>
        <Grid items={clients} style={{ height: '100%' }}>
          <GridColumn path="id" header="ID" />
          <GridColumn path="name" header="Name" />
          <GridColumn path="contactInfo" header="Contact Info" />
        </Grid>
      </div>
    </Dialog>
  );
}