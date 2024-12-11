package med.voll.api.domain.medico;

public enum Especialidad {
    PEDIATRIA("ortopedia"),
    GINECOLOGIA("ginecologia"),
    CARDIOLOGIA("cardiologia"),
    ORTOPEDIA("ortopedia");

    private String especialidad;

     Especialidad(String especialidad){
        this.especialidad=especialidad;
    }

    public String getEspecialidad(){
         return especialidad;
    }

    public static  Especialidad fromString(String texto){
         for(Especialidad esp : Especialidad.values()){
             if((esp.getEspecialidad().equalsIgnoreCase(texto))){
                 return esp;
             }
        }
         return null;
    }
}
