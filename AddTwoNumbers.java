/*
 * 問題:
 * ユーザーにキーボードから数字を2つ入力してもらい、
 * その合計を画面に表示するプログラムを作る。
 *
 * 実行例:
 * 1つ目の数字を入力してください: 3
 * 2つ目の数字を入力してください: 5
 * 合計は 8 です
 */
import java.util.Scanner;

public class AddTwoNumbers {
    public static void main(String[] args) {
        // ここにコードを書いてみましょう
        // ヒント: キーボードからの入力を受け取るには Scanner クラスを使います
        // 例: Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.print("1つ目の数字を入力して下さい");
        int num1 = sc.nextInt();
        System.out.print("2つ目の数字を入力して下さい");
        int num2 = sc.nextInt();
        int sum = num1 + num2;
        System.out.println("合計は" + sum + "です");
        sc.close();
    }
}
