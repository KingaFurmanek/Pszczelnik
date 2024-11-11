import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import {AuthProvider} from "./AuthContext.jsx";
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

ReactDOM.render(
    <AuthProvider>
        <App />
    </AuthProvider>,
    document.getElementById('root')
);
