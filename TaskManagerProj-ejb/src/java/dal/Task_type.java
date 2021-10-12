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
    OBI_SAVZ(1) {
        @Override
        public String getRusName() {
            return "ОБИ_САВЗ";
        }
    },
    OBI_ZI(2) {
        @Override
        public String getRusName() {
            return "ОБИ_ЗИ";
        }
    },
    OBI_TZI(3) {
        @Override
        public String getRusName() {
            return "ОБИ_ТЗИ";
        }
    },
    REGIME(4) {
        @Override
        public String getRusName() {
            return "Режим";
        }
    },
    SO(5) {
        @Override
        public String getRusName() {
            return "СО";
        }
    },
    TSS(6) {
        @Override
        public String getRusName() {
            return "ТСС";
        }

    },
    UNKNOWN(7) {
        @Override
        public String getRusName() {
            return "Не определено";
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

    public static Integer getTaskCodeByRUname(String TaskName) {

        switch (TaskName) {
            case ("ОБИ_САВЗ"):
                return Task_type.OBI_SAVZ.getTypeCode();
            case ("ОБИ_ЗИ"):
                return Task_type.OBI_ZI.getTypeCode();
            case ("ОБИ_ТЗИ"):
                return Task_type.OBI_TZI.getTypeCode();
            case ("Режим"):
                return Task_type.REGIME.getTypeCode();
            case ("СО"):
                return Task_type.SO.getTypeCode();
            case ("ТСС"):
                return Task_type.TSS.getTypeCode();
            default:
                return Task_type.UNKNOWN.getTypeCode();
        }
    }

    public static String getTaskRUnameByCode(Integer TaskTypeCode) {
        switch (TaskTypeCode) {
            case (1):
                return Task_type.OBI_SAVZ.getRusName();
            case (2):
                return Task_type.OBI_ZI.getRusName();
            case (3):
                return Task_type.OBI_TZI.getRusName();
            case (4):
                return Task_type.REGIME.getRusName();
            case (5):
                return Task_type.SO.getRusName();
            case (6):
                return Task_type.TSS.getRusName();
            default:
                return Task_type.UNKNOWN.getRusName();
        }
    }

}
