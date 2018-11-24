
public class Main {
    public static void main(String[] args) {
        try{
            String[] arr = new String[]{"1", "2", "3"};
            doRun(arr);
        }catch (MyArraySizeException e){
            System.out.println(e);
        }
        try{
            String[][] arr = new String[][]{{"1"}, {"не число"}, {"3"}};
            doRun(arr);
        }catch (MyArrayDataException e){
            System.out.println(e);
        }
    }

    private static void doRun(Object arg){
        if(!(arg instanceof String[][]))
            throw new MyArraySizeException("Не верный размер массива!");
        else {
            String[][] arr = (String[][]) arg;
            int temp;
            for (int i = 0; i <arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    try{
                        temp = Integer.parseInt(arr[i][j]);
                    }catch (RuntimeException e){
                        throw new MyArrayDataException("Не верные данные в элементе " + i + " " + j);
                    }
                }
            }
        }
    }
}
