import BaseLayout from "../layouts/BaseLayout";
import { useEffect, useState } from "react";
import { getPeople, savePerson } from "../services/peopleService";
import Swal from "sweetalert2";
import { useNavigate } from "react-router";
import { Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, InputAdornment, TextField } from "@mui/material";
import { PeopleAlt } from "@mui/icons-material";
import { DataGrid } from "@mui/x-data-grid";

export default function People() {
    const [people, setPeople] = useState([]);
    const [personName, setPersonName] = useState("");
    const [personPhoneNumber, setPersonPhoneNumber] = useState("");
    const [personCpf, setPersonCpf] = useState("");
    const [search, setSearch] = useState("");
    const [modalOpen, setModalOpen] = useState(false);
    const [selectedRow, setSelectedRow] = useState([]);

    let navigator = useNavigate();

    const fetchPeople = async () => {
        const result = await getPeople();

        if (!result.success) {
            Swal.fire({
                title: "Erro!",
                text: "Erro ao tentar carregar pessoas cadastradas!",
                icon: "error"
            }).then(() => {
                if (result.body.statusCode === 401) {
                    navigator("/");
                }
            });
        }

        setPeople(result.body.data);
    }

    useEffect(() => {
        fetchPeople();
    }, []);

    const filteredPeople = people.filter((person) => person.name.toLowerCase().includes(search.toLowerCase()));

    const handleCloseModal = () => {
        setModalOpen(false);
    }

    const handleSaveClick = async () => {
        const result = await savePerson({
            name: personName,
            cpf: personCpf,
            phoneNumber: personPhoneNumber
        });

        if (!result.success) {
            console.log("deu ruim salvar")
            return;
        }

        setPeople([...people, result.body.data]);

        Swal.fire({
            title: "Sucesso",
            text: "Pessoa salva com sucesso ID: " + result.body.data.id,
        });
    }

    const handleSelectRow = (id) => {
        console.log(id)
        setSelectedRow(id.ids.values().next().value());
    }

    return (
        <>
            <BaseLayout>
                <Box
                    sx={{
                        display: "flex",
                        flexDirection: "column",
                        p: 2,
                    }}
                >

                    <Box
                        sx={{
                            display: "flex",
                            flexWrap: "wrap"
                        }}
                    >
                        <TextField
                            type="text"
                            placeholder="Busque por nome de uma pessoa"
                            label="Pessoas"
                            slotProps={{
                                input: {
                                    endAdornment: (
                                        <InputAdornment position="end">
                                            <PeopleAlt />
                                        </InputAdornment>
                                    )
                                }
                            }}
                            onChange={(e) => setSearch(e.target.value)}
                        />

                        <Button color="error" variant="contained">Excluir</Button>
                        <Button color="primary" variant="contained" onClick={() => setModalOpen(true)}>{selectedRow.length !== 0 ? "Editar" : "Adicionar"}</Button>
                    </Box>
                    <DataGrid
                        columns={[
                            { field: "id", headerName: "#"},
                            { field: "name", headerName: "Nome"},
                            { field: "cpf", headerName: "C.P.F"},
                        ]}
                        rows={filteredPeople}
                        initialState={{
                            pagination: {
                                paginationModel: { pageSize: 10 }
                            }
                        }}
                        onRowSelectionModelChange={(id) => handleSelectRow(id)}
                    />
                </Box>

            </BaseLayout>
            <Dialog
                open={modalOpen}
                onClose={handleCloseModal}
                component="form"
            >
                <DialogTitle>Salvar Pessoa</DialogTitle>
                <DialogContent>
                    <TextField autoFocus fullWidth required type="text" placeholder="Digite o nome" label="Nome" margin="dense" onChange={(e) => setPersonName(e.target.value)} />
                    <TextField fullWidth required type="number" placeholder="Digite o cpf" label="C.P.F" margin="dense" onChange={(e) => setPersonCpf(e.target.value)} />
                    <TextField fullWidth required type="text" placeholder="Digite o telefone" label="Telefone" margin="dense" onChange={(e) => setPersonPhoneNumber(e.target.value)} />
                </DialogContent>
                <DialogActions>
                    <Button color="error" variant="contained" onClick={handleCloseModal}>Cancelar</Button>
                    <Button color="primary" variant="contained" onClick={handleSaveClick}>Salvar</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}