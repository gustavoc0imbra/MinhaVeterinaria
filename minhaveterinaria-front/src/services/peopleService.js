import axios from "axios";
const API_PEOPLE = "http://localhost:8081/api/v0/people";


export async function getPeople() {
    const token = localStorage.getItem("token");
    try {
        const response = await axios.get(API_PEOPLE, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        return { success: true, body: { data: response.data, statusCode: response.status } };
    } catch (exc) {
        console.log(exc)
        return { success: false, body: { message: exc, statusCode: exc.status } };
    }

}

export async function savePerson(person) {
    const token = localStorage.getItem("token");
    try {
        const response = await axios.post(API_PEOPLE, person, {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            },
        });

        return { success: true, body: { data: response.data, statusCode: response.status } };
    } catch (exc) {
        console.log(exc)
        return { success: false, body: { message: exc, statusCode: exc.status } };
    }

}

export async function deleteByIdPerson(id) {
    const token = localStorage.getItem("token");
    try {
        const response = await axios.delete(`${API_PEOPLE}/${id}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        return { success: true, body: { data: response.data, statusCode: response.status } };
    } catch (exc) {
        console.log(exc)
        return { success: false, body: { message: exc, statusCode: exc.status } };
    }

}