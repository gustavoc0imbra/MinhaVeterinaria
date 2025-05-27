import axios from "axios";

const API_USER = "http://localhost:8080/api/v0/login";

export async function login(email, password) {
    const result = await axios.post(API_USER, {email, password});
    
    return result;
}