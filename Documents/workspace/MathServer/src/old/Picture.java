package old;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Picture")
public class Picture {

	
	@Id
    @GeneratedValue
   private Integer id;
   private String src;
   private Integer number;
   
   private Picture() {}
   
   public Picture(String src, int number) {
      this.src = src;
      this.number = number;
   }
   
   public String toString() {
       return "Picture [id=" + id + ", src=" + src + ", number=" + number + "]";
   }
   
   public Integer getId() {
      return id;
   }
   private void setId(Integer id) {
      this.id = id;
   }
   public String getSrc() {
      return src;
   }
   public void setText(String src) {
      this.src = src;
   }
   public Integer getNumber() {
      return number;
   }
}