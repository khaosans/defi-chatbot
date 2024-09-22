import React from 'react';
import { Button } from "@vaadin/react-components/Button";

interface CustomButtonProps {
  onClick?: () => void;
  className?: string;
  children: React.ReactNode;
  disabled?: boolean;
  bgColor?: string; // Add bgColor prop
}

const CustomButton: React.FC<CustomButtonProps> = ({ onClick, className, children, disabled, bgColor = 'bg-blue-500' }) => {
  return (
    <Button
      onClick={onClick}
      className={`${bgColor} hover:bg-blue-600 text-white font-bold py-2 px-4 rounded transition duration-150 ease-in-out ${className}`} // Use bgColor prop
      disabled={disabled}
    >
      {children}
    </Button>
  );
};

export default CustomButton;