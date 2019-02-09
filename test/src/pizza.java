public class pizza {

    public static void main(String[] args) {
        int r=3;
        int c=5;
        int m=1;
        int x=6;
        int t_count=0;
        int m_count=0;

        String[][] arr=new String[3][5];
        arr[0][0]="T";
        arr[0][1]="T";
        arr[0][2]="T";
        arr[0][3]="T";
        arr[0][4]="T";

        arr[1][0]="T";
        arr[1][1]="M";
        arr[1][2]="M";
        arr[1][3]="M";
        arr[1][4]="T";

        arr[2][0]="T";
        arr[2][1]="I";
        arr[2][2]="I";
        arr[2][3]="I";
        arr[2][4]="I";

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
                if(arr[i][j].equals("T")){
                    t_count++;
                }
                else if(arr[i][j].equals("M")){
                    m_count++;
                }
            }
            System.out.println();
        }
        System.out.println(m_count+""+t_count);




    }
}
