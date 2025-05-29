import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";

export default function SimpleTable({ ariaLabel, columns, data, sx }) {
    return (
        <>
            <TableContainer component={Paper}>
                <Table aria-label={ariaLabel} sx={sx}>
                <TableHead>
                    <TableRow>
                        {
                            columns.map((column, index) => (
                                <TableCell key={index}>{column.title}</TableCell>
                            ))
                        }
                    </TableRow>
                </TableHead>
                <TableBody>
                    {
                        data.map((item, index) => (
                            <TableRow
                                key={index}
                            >
                                {
                                    columns.map((column) => (
                                        <TableCell>{item[column.key]}</TableCell>
                                    ))
                                }
                            </TableRow>
                        ))
                    }
                </TableBody>
            </Table>
            </TableContainer>
            
        </>
    );
}