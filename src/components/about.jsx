import jugueria from "../assets/img/jugueria.jpg";

import jugos3 from "../assets/img/jugos3.jpg";
export default function About() {
  return (
    <div
      className="w-100 min-vh-100 overflow-hidden position-relative"
      style={{
        background: "linear-gradient(to right, #fdf0e4, #ffb19e)",
        padding: "100px 20px",
      }}
    >
      {/* Marco superior */}
      <img
        src={jugos3}
        alt="Decorativo"
        style={{
          position: "absolute",
          top: 0,
          left: 0,
          width: "100%",
          height: "80px",
          objectFit: "cover",
        }}
      />

      {/* Marco inferior */}
      <img
        src={jugos3}
        alt="Decorativo"
        style={{
          position: "absolute",
          bottom: 0,
          left: 0,
          width: "100%",
          height: "80px",
          objectFit: "cover",
        }}
      />

      {/* Marco izquierdo */}
      <img
        src={jugueria}
        alt="Decorativo"
        style={{
          position: "absolute",
          top: 0,
          left: 0,
          width: "80px",
          height: "100%",
          objectFit: "cover",
        }}
      />

      {/* Marco derecho */}
      <img
        src={jugueria}
        alt="Decorativo"
        style={{
          position: "absolute",
          top: 0,
          right: 0,
          width: "80px",
          height: "100%",
          objectFit: "cover",
        }}
      />

      {/* Contenido dentro del marco */}
      <div
        className="container text-center"
        style={{
          zIndex: 10,
          position: "relative",
        }}
      >
        <h2
          className="mb-4"
          style={{
            fontWeight: "bold",
            fontSize: "50px",
            fontFamily: "Ultra",
            color: "#f95d1f",
            borderBottom: "4px solid #f95d1f",
            display: "inline-block",
            paddingBottom: "5px",
            marginTop: "10px",
          }}
        >
          Sobre Nosotros
        </h2>
        <p
          className="mx-auto"
          style={{
            fontSize: "1.2rem",
            color: "#333",
            maxWidth: "800px",
            lineHeight: "1.7",
            backgroundColor: "rgba(255, 255, 255, 0.85)",
            padding: "20px",
            borderRadius: "15px",
            marginTop: "20px",
          }}
        >
          En <strong>Sabor del Sol</strong>, nos apasiona ofrecerte la mejor experiencia
          de jugos naturales. Nuestros jugos están hechos con frutas frescas y
          orgánicas, seleccionadas cuidadosamente para brindarte un sabor
          auténtico y saludable. Creemos en el poder de la naturaleza para
          refrescar tu día y llenarte de energía con cada sorbo.
        </p>
      </div>
    </div>
  );
}
