import React from "react";
import logo from "../assets/img/logo.png";
import { FaHome, FaUtensils, FaStar, FaUser, FaShoppingCart } from "react-icons/fa";

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg bg-white shadow-sm px-5 sticky-top">
      <div className="container-fluid">
        <a className="navbar-brand d-flex align-items-center gap-2" href="#inicio">
          <img src={logo} alt="Logo Sabor del Sol" width="50" />
          <strong>Sabor del Sol</strong>
        </a>
        <ul className="navbar-nav ms-auto d-flex flex-row gap-3">
          <li className="nav-item">
            <a className="nav-link d-flex align-items-center" href="#inicio" style={{ color: "#d84315", fontWeight: "500" }}>
              <FaHome className="me-1" /> Inicio
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link d-flex align-items-center" href="#catalogo" style={{ color: "#d84315", fontWeight: "500" }}>
              <FaUtensils className="me-1" /> Catálogo
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link d-flex align-items-center" href="#promociones" style={{ color: "#d84315", fontWeight: "500" }}>
              <FaStar className="me-1" /> Promociones
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#" style={{ color: "#d84315" }}>
              <FaUser />
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#" style={{ color: "#d84315" }}>
              <FaShoppingCart />
            </a>
          </li>
        </ul>
      </div>
    </nav>
  );
}
