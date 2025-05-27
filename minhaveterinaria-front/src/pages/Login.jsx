import { Box, Button, Container, TextField } from "@mui/material";
import BaseLayout from "../layouts/BaseLayout";
import { useState } from "react";
import { login } from "../services/userService";

export default function Login() {
    const email = useState("");
    const password = useState("");
    
    const handleLogin = async () => {
        const result = await login(email, password);

        console.log(result);
    }

    return (
        <>
            <BaseLayout>
                <Container>
                    <Box
                        component={"form"}
                        sx={{
                            height: "100vh",
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "center",
                            gap: 2,
                        }}
                    >
                        <TextField label="E-mail" type="email" />
                        <TextField label="Senha" type="password" />
                        <Button variant="contained" onClick={handleLogin}>Entrar</Button>
                    </Box>
                </Container>
            </BaseLayout>

        </>
    );
}