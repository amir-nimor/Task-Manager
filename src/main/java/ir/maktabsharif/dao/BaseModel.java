package ir.maktabsharif.dao;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseModel <ID extends Number>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
}
