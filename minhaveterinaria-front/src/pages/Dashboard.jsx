import { Box, Card, CardActionArea, CardContent, CardMedia, Typography } from "@mui/material";
import BaseLayout from "../layouts/BaseLayout";
import { People } from "@mui/icons-material";
import img from "../assets/cacau.jpg";
import personImg from "../assets/person.png";
import { useNavigate } from "react-router";

export default function Dashboard() {

    let navigator = useNavigate();


    const navigateTo = (route) => {
        navigator(route);
    }

    return (
        <>
            <BaseLayout>
                <Box component="main" sx={{ display: "flex", flexWrap: "wrap", gap: 2, p: 3 }}>
                    <Card>
                        <CardActionArea onClick={navigateTo("/animals")}>
                            <Box
                                sx={{
                                    position: "relative",
                                    "::before": {
                                        content: '""',
                                        position: "absolute",
                                        width: "100%",
                                        height: "100%",
                                        background: "linear-gradient(transparent 0%, #000)"
                                    },
                                }}
                            >
                                <CardMedia
                                    component={"img"}
                                    height="200"
                                    image={img}
                                    sx={{
                                        objectPosition: "center top",
                                    }}
                                />
                            </Box>
                        </CardActionArea>
                        <CardContent>
                            <Typography variant="h5">Animais</Typography>
                            <Typography variant="body1">Clique para gerenciar os animais</Typography>
                        </CardContent>
                    </Card>
                    <Card>
                        <CardActionArea>
                            <Box
                                sx={{
                                    position: "relative",
                                    "::before": {
                                        content: '""',
                                        position: "absolute",
                                        width: "100%",
                                        height: "100%",
                                        background: "linear-gradient(transparent 0%, #000)"
                                    }
                                }}
                            >
                               <CardMedia
                                    component={"img"}
                                    height="200"
                                    image={personImg}
                                    /* sx={{
                                        objectPosition: "center top",
                                    }} */
                               />
                            </Box>
                        </CardActionArea>
                        <CardContent>
                            <Typography variant="h5">Pessoas</Typography>
                            <Typography variant="body1">Clique para gerenciar as pessoas</Typography>
                        </CardContent>
                    </Card>
                </Box>
            </BaseLayout>

            
        </>
    );
}