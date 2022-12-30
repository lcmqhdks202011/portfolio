public abstract class Generator {
    
	protected static String generateNumMembre(){
    	String code = generateNum(9);
        if(isMemberCodeRedundant(code)) {
        	generateNumMembre();
        };
		return code;
    }

    protected static String generateNumPro(){
    	String code = generateNum(9);
        if(isProCodeRedundant(code)) {
        	generateNumPro();
        };
		return code;
    }
    
    protected static String generateNumService(){
    	String code = generateNum(3);
        if(isServiceCodeRedundant(code)) {
        	generateNumService();
        };
		return code;
    }

    protected static String generateNumSession(Service s){
    	String idService = s.getUID();
    	String idSession = generateNum(2);
    	String last2Pro = s.getPro().substring(7);
        String codeSession = idService + idSession + last2Pro;
        
        //Verifie la redondance du code pour qu'il soit unique
        for(Session sess : s.sessions) {
        	if(codeSession.equals(sess.getCodeSession())) {
        		generateNumSession(s);
        	}
        }
        return codeSession;
    }
    


    protected static String generateNum(int size){
        StringBuilder codeGenere = new StringBuilder();
        for(int i=0 ; i<size; i++){
            int num = (int) (Math.random() * 9);
            codeGenere.append(num);
        }
        return codeGenere.toString();
    }

    
    protected static boolean isServiceCodeRedundant(String codeService){

        ControlleurServices contS = ControlleurServices.getInstance();
        
        for(Service s : contS.services){
        	if(codeService.equals(s.getUID())){
            	return true;
            	}
        	}
        return false;
    }
    
    protected static boolean isProCodeRedundant(String codeProfessionnel){

        ControlleurProfessionnel contP = ControlleurProfessionnel.getInstance();
        
        {
            for(Professionnel p : contP.pros){
                if(codeProfessionnel.equals(p.getCodeProfessionnel())){
                    return true;
                }
            }

            return false;
        }
    }
    
    protected static boolean isMemberCodeRedundant(String codeMembre){

        ControlleurMembre contM = ControlleurMembre.getInstance();
        
        for(Membre m : contM.membres){
        	if(codeMembre.equals(m.getCodeMembre())){
        		return true;
        	}
        }
        return false;
    }
}