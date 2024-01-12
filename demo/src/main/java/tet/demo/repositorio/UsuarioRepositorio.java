package tet.demo.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tet.demo.models.UsuarioModel;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioModel, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}