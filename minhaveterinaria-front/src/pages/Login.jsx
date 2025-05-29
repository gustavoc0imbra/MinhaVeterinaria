import { Box, Button, Container, TextField, Typography } from "@mui/material";
import BaseLayout from "../layouts/BaseLayout";
import { useState } from "react";
import { login } from "../services/userService";
import img from "../assets/cacau.jpg";
import Image from "../components/Image";
import Swal from "sweetalert2";
import { useNavigate } from "react-router";

export default function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    let navigate = useNavigate();
    
    const handleLogin = async () => {
        const result = await login(email, password);

        Swal.fire({
            title: "Login",
            icon: result.success ? "success" : "error",
            text: result.body.message
        }).then(() => {

            if(result.success) {
                localStorage.setItem("token", result.body.token);
                navigate("/dashboard");
            }

        });
    }

    return (
        <>
            <Container>
                <Box
                    sx={{
                        height: "100vh",
                        display: {
                           xs: "flex",
                           s: "inline"
                        },
                        flexDirection: "column",
                        justifyContent: "center",
                        alignContent: "center",
                        gap: 2
                    }}
                >
                    <Image img={img} isRounded={true} />
                    <Box
                        component={"form"}
                        sx={{
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "center",
                            alignContent: "center",
                            gap: 2,
                        }}
                    >
                        <Typography aria-label="Minha Veterinária título" textAlign={"center"} variant="h4">
                            Minha Veterinária
                        </Typography>
                        <TextField onChange={(e) => setEmail(e.target.value)} label="E-mail" type="email" />
                        <TextField onChange={(e) => setPassword(e.target.value)} label="Senha" type="password" />
                        <Button variant="contained" onClick={handleLogin}>Entrar</Button>
                    </Box>
                </Box>
            </Container>
        </>
    );
}