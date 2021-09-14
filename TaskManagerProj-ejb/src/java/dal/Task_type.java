package dal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public enum Task_type {
    OBI_SAVZ(1){
        @Override
        public String getRusName() {
        return "ОБИ_САВЗ";
        }
    },
    OBI_ZI(2){
        @Override
        public String getRusName() {
        return "ОБИ_ЗИ";
        }
    },
    OBI_TZI(3){
        @Override
        public String getRusName() {
        return "ОБИ_ТЗИ";
        }
    },
    REGIME(4){
        @Override
        public String getRusName() {
        return "Режим";
        }
    },
    SO(5){
        @Override
        public String getRusName() {
        return "СО";
        }
    },
    TSS(6){
        @Override
        public String getRusName() {
        return "ТСС";
        }
    };

    private final Integer typeCode;

    Task_type(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    @Override
    public String toString() {
        return "Task_type{" + "typeCode=" + typeCode + '}';
    }

    public String getRusName() {
        return "Российское сокращение";
    }

}
