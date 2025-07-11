# 🧃 Sabor del Sol - Sistema Web de Pedidos y Personalización de Jugos

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring--Boot-3.5.3-green)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)
![Pattern](https://img.shields.io/badge/Design%20Patterns-Command%2C%20Observer%2C%20Strategy%2C%20Factory%2C%20Decorator-orange)
![Status](https://img.shields.io/badge/Estado-En%20Desarrollo-yellow)

---

## 📌 Descripción

**Frontend construido con React + Bootstrap 5** para una experiencia rápida y responsiva.

**Sabor del Sol** es un sistema web que permite a los usuarios:

- Personalizar jugos seleccionando ingredientes extras 🧃
- Elegir combos predefinidos 🍍🍌
- Aplicar promociones activas 🎁
- Realizar pedidos y ver su estado en tiempo real 📦
- Recibir notificaciones por cambios de estado (SSE) 🔔
- Administrar promociones desde un panel seguro para el administrador 🔒

---

## 🛠️ Tecnologías usadas
| Tecnología       | Uso                                |
|------------------|-------------------------------------|
| React JS         | Interfaz del cliente (frontend SPA) |
| Bootstrap 5      | Estilos rápidos y responsivos       |
|------------------|-------------------------------------|
| Java 21          | Lenguaje principal                  |
| Spring Boot 3.5  | Framework backend                   |
| Spring Security  | Seguridad y roles con JWT          |
| MySQL / Azure    | Base de datos                       |
| Lombok           | Reducción de código repetitivo      |
| SseEmitter       | Notificaciones en tiempo real (SSE) |
| JPA / Hibernate  | Persistencia de entidades           |

---

## 🧠 Patrones de Diseño Aplicados

| Patrón        | Aplicación específica                             |
|---------------|----------------------------------------------------|
| **Decorator** | Añadir ingredientes extra a una bebida             |
| **Command**   | Encapsular acciones de agregar productos al pedido |
| **Factory**   | Crear combos prediseñados                          |
| **Strategy**  | Aplicar diferentes tipos de promociones dinámicamente |
| **Observer**  | Notificaciones SSE por cambio de estado de pedido y promociones |
| **Builder** (opcional) | Construcción fluida de objetos `Pedido`         |

---

## 📦 Estructura de Carpetas (Backend)

```
src/
├── controller/
│   └── PedidoController, PromocionController, NotificationController
├── dto/
│   └── PedidoRequestDTO, ComboRequestDTO, etc.
├── models/
│   └── entity/
│       └── Pedido, DetallePedido, Promocion, etc.
├── observer/
│   ├── pedido/
│   │   └── PedidoNotifier, ClientePedidoSseObserver
│   └── promocion/
│       └── PromocionPublisher, ClienteSseObserver
├── services/
│   └── PedidoService, PromocionService
├── utils/
│   └── JwtUtil, etc.
```

---

## 🔐 Roles y Seguridad

- **ADMIN** puede:
    - Gestionar promociones
    - Ver todos los pedidos y cambiar sus estados
- **CLIENTE** puede:
    - Realizar pedidos
    - Visualizar su historial
    - Recibir notificaciones por SSE

> Implementado con Spring Security + JWT y autorización por endpoint.

---

## 🔔 Notificaciones en tiempo real (SSE)

| Evento            | Endpoint de conexión SSE                  | Evento recibido   |
|-------------------|-------------------------------------------|-------------------|
| Pedido actualizado| `/api/notificaciones/pedidos`             | `pedido-estado`   |
| Promoción creada  | `/api/notificaciones/promociones`         | `promocion-nueva` |

---

## 📋 Endpoints clave

| Método | Endpoint                        | Descripción                        |
|--------|----------------------------------|-------------------------------------|
| `GET`  | `/api/promociones/activas`      | Lista promociones activas          |
| `POST` | `/api/pedidos`                  | Crear pedido personalizado         |
| `POST` | `/api/pedidos/agregar-combo`    | Crear pedido desde combo (Factory) |
| `PUT`  | `/api/pedidos/{id}/estado`      | Cambiar estado de pedido (ADMIN)   |

---

## 🧪 Pruebas en Postman

- Incluye autenticación con `Bearer Token`
- Puede probar notificaciones usando múltiples clientes abiertos al mismo tiempo

---

## 🎨 Futuras mejoras

- Dashboard gráfico con métricas 📊
- Exportación de pedido a PDF 📄
- Historial de cambios de estado 📜
- Integración con frontend React / Vue ⚛️

---

## 👨‍💻 Autor

Desarrollado por **Josue R. Tanta Cieza, Katherine Salas & Luis Angel Blas**  
Estudiante de Ingeniería de Software – UTP  
🔗 [LinkedIn](https://linkedin.com/in/tu-nombre) | 💼 [Portafolio](https://tu-portafolio.com)

---
