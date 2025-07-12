import React from "react";

export default function ModalLogin({ show, onClose }) {
  if (!show) return null;

  return (
    <div style={backdropStyle}>
      <div style={modalStyle}>
        <button onClick={onClose} style={closeStyle}>✖</button>
        <h3 style={{ color: "#f95d1f", marginBottom: "20px" }}>Iniciar sesión</h3>
        
        <form>
          <input type="email" placeholder="Correo electrónico" style={inputStyle} required />
          <input type="password" placeholder="Contraseña" style={inputStyle} required />
          <button type="submit" style={btnStyle}>Entrar</button>
        </form>

        <p style={{ marginTop: "15px", fontSize: "14px" }}>
          ¿No tienes cuenta? <a href="#" style={{ color: "#f95d1f" }}>Regístrate</a>
        </p>
      </div>
    </div>
  );
}

const backdropStyle = {
  position: "fixed",
  top: 0, left: 0, right: 0, bottom: 0,
  backgroundColor: "rgba(0, 0, 0, 0.5)",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  zIndex: 9999,
};

const modalStyle = {
  backgroundColor: "#fff",
  padding: "30px 20px",
  borderRadius: "10px",
  width: "320px",
  boxShadow: "0 0 10px rgba(0, 0, 0, 0.3)",
  position: "relative",
};

const closeStyle = {
  position: "absolute",
  top: "10px",
  right: "10px",
  border: "none",
  background: "transparent",
  fontSize: "18px",
  cursor: "pointer",
};

const inputStyle = {
  width: "100%",
  padding: "10px",
  marginBottom: "12px",
  border: "1px solid #ccc",
  borderRadius: "5px",
  fontSize: "14px",
};

const btnStyle = {
  width: "100%",
  padding: "10px",
  backgroundColor: "#f95d1f",
  color: "white",
  border: "none",
  borderRadius: "5px",
  fontSize: "16px",
  cursor: "pointer",
};
