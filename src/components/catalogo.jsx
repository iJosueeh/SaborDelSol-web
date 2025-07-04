
export default function Catalogo() {
  return (
    <div
      className="w-100 min-vh-100 overflow-hidden"
      style={{
        background: "linear-gradient(to right, #fdf0e4, #ffb19e)",
      }}
    >
      <div className="container my-5">
        <h2
          className="mb-4"
          style={{
            fontWeight: "bold",
            fontSize: "2rem",
            color: "black",
            borderBottom: "4px solid #f95d1f",
            display: "inline-block",
            paddingBottom: "1px",
            marginLeft: "10px",
            marginTop: "40px"
          }}
        >
          CATÁLOGO
        </h2>
        {/* Aquí puedes agregar el contenido del catálogo */}
      </div>
    </div>
  );
}