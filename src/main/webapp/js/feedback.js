/* Retroalimentación de DroneCheck: mensajes claros, estados y validación. */
document.addEventListener("DOMContentLoaded", function () {
    function mostrarMensaje(elemento, texto, tipo) {
        if (!elemento) return;
        elemento.textContent = texto;
        elemento.className = "mensaje-feedback " + tipo;
        elemento.hidden = false;
    }

    const login = document.getElementById("form-login");
    if (login) {
        const correo = document.getElementById("correo");
        const password = document.getElementById("contrasenia");
        const mensaje = document.getElementById("mensaje-login");
        const estado = document.getElementById("estado-login");
        const boton = document.getElementById("boton-login");

        login.addEventListener("submit", function (event) {
            event.preventDefault();
            mensaje.hidden = true;

            if (!correo.value.trim() || !password.value) {
                mostrarMensaje(mensaje, "Completa tu correo electrónico y contraseña para continuar.", "error");
                (!correo.value.trim() ? correo : password).focus();
                return;
            }
            if (!correo.validity.valid) {
                mostrarMensaje(mensaje, "Revisa el formato del correo. Ejemplo: nombre@correo.com", "error");
                correo.focus();
                return;
            }

            boton.disabled = true;
            boton.textContent = "Procesando...";
            estado.textContent = "Estamos enviando tu solicitud de inicio de sesión.";
            mostrarMensaje(mensaje, "Solicitud recibida. Verificando los datos…", "progreso");
            login.submit();
        });
    }

    const registro = document.getElementById("form-registro");
    if (registro) {
        const nombre = document.getElementById("full_name");
        const usuario = document.getElementById("user_name");
        const correo = document.getElementById("correo");
        const password = document.getElementById("contrasenia");
        const confirmar = document.getElementById("confirmar_contrasenia");
        const mensaje = document.getElementById("mensaje-registro");
        const estado = document.getElementById("estado-registro");

        registro.addEventListener("submit", function (event) {
            event.preventDefault();
            mensaje.hidden = true;

            const campos = [nombre, usuario, correo, password, confirmar];
            const vacio = campos.find(campo => !campo.value.trim());
            if (vacio) {
                mostrarMensaje(mensaje, "Hay campos pendientes. Complétalos para continuar.", "error");
                vacio.focus();
                return;
            }
            if (!correo.validity.valid) {
                mostrarMensaje(mensaje, "El correo no tiene un formato válido. Revisa que incluya un usuario y un dominio.", "error");
                correo.focus();
                return;
            }
            if (password.value.length < 8) {
                mostrarMensaje(mensaje, "La contraseña debe tener al menos 8 caracteres.", "error");
                password.focus();
                return;
            }
            if (password.value !== confirmar.value) {
                mostrarMensaje(mensaje, "Las contraseñas no coinciden. Escríbelas nuevamente para que sean iguales.", "error");
                confirmar.focus();
                return;
            }
            const terminos = registro.querySelector('input[name="terminos"]');
            if (!terminos.checked) {
                mostrarMensaje(mensaje, "Para continuar, debes aceptar los términos y condiciones.", "advertencia");
                terminos.focus();
                return;
            }

            mostrarMensaje(mensaje, "¡Correcto! Todos los campos están completos y las contraseñas coinciden.", "exito");
            estado.textContent = "Importante: el registro de cuentas todavía no está conectado; aún no se ha creado una cuenta.";
        });
    }

    // Confirmación visual al mostrar u ocultar una contraseña.
    document.querySelectorAll(".mostrar-password").forEach(function (boton) {
        boton.addEventListener("click", function () {
            const id = boton.dataset.target || (boton.closest(".input-icono")?.querySelector("input")?.id);
            const campo = id ? document.getElementById(id) : null;
            if (!campo) return;
            const visible = campo.type === "password";
            campo.type = visible ? "text" : "password";
            boton.setAttribute("aria-pressed", String(visible));
            boton.setAttribute("aria-label", visible ? "Ocultar contraseña" : "Mostrar contraseña");
            boton.textContent = visible ? "◌" : "◉";
        });
    });

    // Advertencia al salir si el usuario ya comenzó a llenar el registro.
    const formularioRegistro = document.getElementById("form-registro");
    if (formularioRegistro) {
        let modificado = false;
        formularioRegistro.addEventListener("input", () => { modificado = true; });
        formularioRegistro.addEventListener("change", () => { modificado = true; });
        window.addEventListener("beforeunload", function (event) {
            if (modificado) {
                event.preventDefault();
                event.returnValue = "";
            }
        });
        formularioRegistro.addEventListener("submit", () => { modificado = false; });
    }
});
