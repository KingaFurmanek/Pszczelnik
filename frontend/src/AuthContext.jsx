import { createContext, useState, useEffect } from 'react';
import {jwtDecode} from "jwt-decode";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [userRole, setUserRole] = useState(null);
    const [userId, setUserId] = useState(null);

    const updateAuth = () => {
        const token = localStorage.getItem('token');
        if (token) {
            try {
                const decoded = jwtDecode(token);
                console.log("Decoded JWT:", decoded);

                const role = decoded.authorities;
                const id = decoded.userId;

                setUserRole(role);
                setUserId(id);
                console.log("User role set to:", role);
                console.log("User ID set to:", id);
            } catch (error) {
                console.error("Invalid token", error);
                setUserRole(null);
                setUserId(null);
            }
        } else {
            setUserRole(null);
            setUserId(null);
        }
    };

    useEffect(() => {
        updateAuth();
    }, []);

    return (
        <AuthContext.Provider value={{ userRole, userId, updateAuth }}>
            {children}
        </AuthContext.Provider>
    );
};
