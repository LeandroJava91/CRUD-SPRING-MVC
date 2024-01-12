package tet.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tet.demo.models.UsuarioModel;
import tet.demo.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class Controlador {

    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @GetMapping("/home")
    public String mostrarPagina(Model model) {
        // Puedes agregar atributos al modelo si necesitas datos en tu plantilla Thymeleaf
        model.addAttribute("mensaje", "Hola desde el controlador");

        // Devuelve el nombre de la plantilla Thymeleaf (sin la extensión)
        return "home";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario() {
        return "formulario";
    }

    @PostMapping("/saludo")
    public String mostrarSaludo(@RequestParam String nombre, @RequestParam String apellido, Model model) {
        model.addAttribute("nombre", nombre);
        model.addAttribute("apellido", apellido);
        return "saludo";
    }

    @PostMapping("/guardarusuario")
    public String guardarUsuario(@ModelAttribute UsuarioModel usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/formulario";
    }

    @GetMapping("/usuarios")
    public String mostrarUsuarios(Model model) {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios",usuarios);
        System.out.println("Usuarios recuperados: " + usuarios);
        return "usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        UsuarioModel usuario = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "formulario-edicion";
    }

    @PostMapping("/guardar-cambios")
    public String guardarCambios(@ModelAttribute UsuarioModel usuario) {
        usuarioRepository.save(usuario);
        System.out.println("holis");
        return "redirect:/usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        // Aquí deberías llamar a tu servicio para realizar la eliminación

        usuarioRepository.deleteById(id);

        // Después de eliminar, podrías redirigir a la página de lista de usuarios
        return "redirect:/usuarios";
    }
}


