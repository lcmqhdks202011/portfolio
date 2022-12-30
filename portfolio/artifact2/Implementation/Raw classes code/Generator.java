public abstract class Generator {
    
    public static String generateNumMembre(){
        return generateNum(9);
    }

    public static String generateNumService(){
        return generateNum(7);
    }

    private static String generateNum(int size){
        StringBuilder codeGenere = new StringBuilder();
        for(int i=0 ; i<size; i++){
            int num = (int) (Math.random() * 9);
            codeGenere.append(num);
        }
        return codeGenere.toString();
    }
}