import { createTheme, ThemeProvider } from "@mui/material";

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