// encapsulated the axios for easy use in other place
export const http = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 5000,
});