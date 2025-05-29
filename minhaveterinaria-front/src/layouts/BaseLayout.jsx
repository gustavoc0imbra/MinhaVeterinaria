import { AppBar, Box, Button, createTheme, Drawer, IconButton, List, ListItemButton, ListItemIcon, ListItemText, ThemeProvider, Toolbar, Typography } from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';

import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import "../assets/base.css";
import { People, Pets } from "@mui/icons-material";
import { useState } from "react";
import { NavLink, useNavigate } from "react-router";

export default function BaseLayout({ children }) {
    const [menuOpen, setMenuOpen] = useState(false);
    let navigator = useNavigate();

    const handleOpenMenu = () => {
        setMenuOpen(true);
    }

    const handleCloseMenu = () => {
        setMenuOpen(false);
    }

    const theme = createTheme({
    });

    const navigateTo = (route) => {

    }

    return (
        <>
            <ThemeProvider theme={theme}>
                <AppBar color="primary" position="static" component="nav">
                    <Toolbar
                        sx={{
                            justifyContent: "space-between"
                        }}
                    >
                        <IconButton sx={{
                            display: {
                                xs: 'block',
                                sm: 'none',
                            }
                        }} onClick={handleOpenMenu}>
                            <MenuIcon />
                        </IconButton>
                        <NavLink to="/dashboard">
                            <Typography variant="h5" sx={{ flex: 1 }}>Minha Veterinária</Typography>
                        </NavLink>
                        <Box
                            sx={{
                                display: "flex",
                                gap: 2
                            }}
                        >
                            <Button color="info" variant="contained" sx={{ display: { xs: 'none', sm: 'block' } }}>Animais</Button>
                            <Button color="info" variant="contained" sx={{ display: { xs: 'none', sm: 'block' } }}>Pessoas</Button>
                        </Box>

                    </Toolbar>
                </AppBar>
                <nav>
                    <Drawer
                        variant="temporary"
                        open={menuOpen}
                        onClose={handleCloseMenu}
                        ModalProps={{
                            keepMounted: true
                        }}
                    >
                        <List>
                            <ListItemButton>
                                <ListItemIcon>
                                    <Pets />
                                </ListItemIcon>
                                <NavLink to="/animals">
                                    <Typography>
                                        Animais
                                    </Typography>
                                </NavLink>
                                {/* <ListItemText primary="Animais" /> */}
                            </ListItemButton>
                            <ListItemButton>
                                <ListItemIcon>
                                    <People />
                                </ListItemIcon>
                                <NavLink to="/people">
                                    <Typography>
                                        Pessoas
                                    </Typography>
                                </NavLink>
                                {/* <ListItemText primary="Pessoas" /> */}
                            </ListItemButton>
                        </List>
                    </Drawer>
                </nav>
                {children}
            </ThemeProvider>
        </>
    );
}