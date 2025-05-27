import { Box, Button, Container, TextField } from "@mui/material";
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
            text: result.message
        }).then(() => {

            if(result.success) {
                navigate("/dashboard");
            }

        });

        console.log(result);
    }

    return (
        <>
            <BaseLayout>
                <Container>
                    
                    <Box
                        sx={{
                            height: "100vh",
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "center",
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
                                gap: 2,
                            }}
                        >
                            <TextField onChange={(e) => setEmail(e.target.value)} label="E-mail" type="email" />
                            <TextField onChange={(e) => setPassword(e.target.value)} label="Senha" type="password" />
                            <Button variant="contained" onClick={handleLogin}>Entrar</Button>
                        </Box>
                    </Box>
                </Container>
            </BaseLayout>

        </>
    );
}