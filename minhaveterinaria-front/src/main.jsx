import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter, Route, Routes } from 'react-router'
import Login from './pages/Login.jsx'
import Dashboard from './pages/Dashboard.jsx'
import People from './pages/People.jsx'

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <StrictMode>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/animals" element={<Dashboard />} />
        <Route path="/people" element={<People />} />
      </Routes>
    </StrictMode>
  </BrowserRouter>,
)
