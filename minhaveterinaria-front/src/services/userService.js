import axios from "axios";

const API_USER = "http://localhost:8080/api/v0/login";

export async function login(email, password) {
    const result = await axios.post(API_USER, {email, password});

    if (result.status !== axios.HttpStatusCode.Ok) {
        return { success: false, body: { message: "E-mail ou senha inválido(s)!"} };
    }
    
    return { success: true, body: { message: result.data.message, token: result.data.token } };
}