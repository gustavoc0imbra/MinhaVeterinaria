import { createTheme, ThemeProvider } from "@mui/material";
import "../assets/base.css";

export default function BaseLayout({ children }) {
    const theme = createTheme();

    return (
        <>
            <ThemeProvider theme={theme}>
                {children}
            </ThemeProvider>
        </>
    );
}