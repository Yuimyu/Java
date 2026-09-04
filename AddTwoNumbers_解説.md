# AddTwoNumbers.java 解説（C言語との比較）

対象コード:

```java
import java.util.Scanner;

public class AddTwoNumbers {
    public static void main(String[] args) {
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
```

`class`・`static`・`new`・`System.out.println`の意味は[HelloWorld_解説.md](HelloWorld_解説.md)で説明済みなので、ここでは**新しく出てきた部分**を中心に解説します。

---

## 1行目: `import java.util.Scanner;`

```java
import java.util.Scanner;
```

Javaの標準ライブラリは「パッケージ」という単位でグループ分けされています。

- `System`や`String`は`java.lang`という**基本パッケージ**に入っていて、これは自動で読み込まれるため書かなくても使えた
- `Scanner`は`java.util`という**別のパッケージ**に入っているクラスなので、使う前に「このパッケージからこのクラスを持ってくるよ」と明示的に宣言する必要がある

これがC言語の`#include <stdio.h>`と似た役割です。Cでは`printf`や`scanf`を使うために`stdio.h`を読み込みましたが、Javaでは「クラス単位」で必要なものだけをピンポイントで`import`します。

書き忘れると `Scanner cannot be resolved to a type`（Scannerという型が見つかりません）というエラーになります。

---

## `Scanner sc = new Scanner(System.in);`

```java
Scanner sc = new Scanner(System.in);
```

これは[HelloWorld_解説.md](HelloWorld_解説.md)の「事前知識4: `new`」で説明した通り、`Scanner`という設計図(クラス)から、キーボード入力を扱うための実体(インスタンス)を1つ作っている行です。

- `System.in`：Cでいう標準入力`stdin`にあたるもの。「キーボードからの入力そのもの」を表すオブジェクト
- `new Scanner(System.in)`：「`System.in`(キーボード入力)を読み取る係」としてScannerの実体を作る
- `sc`：作った実体を、以降`sc`という名前で使えるようにしている変数（C言語でポインタに`malloc`の結果を代入するのと同じ感覚）

C言語では入力を受け取るのに`scanf("%d", &num1);`と書きましたが、Javaでは「まず`Scanner`という専用の道具(オブジェクト)を用意して、その道具を使って読み取る」という2段階の考え方になります。

---

## `System.out.print("1つ目の数字を入力して下さい");`

```java
System.out.print("1つ目の数字を入力して下さい");
```

[HelloWorld_解説.md](HelloWorld_解説.md)で出てきた`println`との違いは、**自動で改行しない**点だけです。

- `println`：出力後に改行する（"print line"）
- `print`：出力するだけで改行しない（"print"）

ここでは「入力してください」と表示した後、同じ行でユーザーの入力を待ちたいので`print`を使っています。

---

## `int num1 = sc.nextInt();`

```java
int num1 = sc.nextInt();
```

- `int num1`：C言語と全く同じで、「`int`型（整数）の変数`num1`を宣言する」という意味
- `sc.nextInt()`：事前知識3で説明した「実体が持っている処理(メソッド)を呼び出す」というパターンそのものです。`sc`という実体(Scanner)が持っている`nextInt()`というメソッドを呼び出して、「キーボードから整数を1つ読み取る」処理を実行しています
- `=`：`nextInt()`が読み取った結果(戻り値)を、左側の`num1`に代入する

C言語の`scanf("%d", &num1);`との一番の違いは、**戻り値として直接値をもらえる**ことです。Cの`scanf`は`&num1`のようにポインタ(アドレス)を渡して「そこに書き込んでもらう」形でしたが、Javaの`sc.nextInt()`は読み取った値を`return`でそのまま返してくれるので、`&`のようなポインタ操作が不要です。

2つ目の入力を受け取る

```java
System.out.print("2つ目の数字を入力して下さい");
int num2 = sc.nextInt();
```

も全く同じ考え方です。

---

## `int sum = num1 + num2;`

```java
int sum = num1 + num2;
```

これはC言語と全く同じ、ただの整数の足し算です。Javaでも`+`, `-`, `*`, `/`, `%`といった算術演算子はCと同じように使えます。

---

## `System.out.println("合計は" + sum + "です");`

```java
System.out.println("合計は" + sum + "です");
```

ここが少しJavaらしい書き方です。`+`は数値の足し算だけでなく、**文字列と文字列（や数値）をつなげる（連結する）ときにも使えます**。

- `"合計は" + sum` → 文字列`"合計は"`の後ろに、`sum`の中身(例えば`8`)がくっついて`"合計は8"`という新しい文字列になる
- さらに`+ "です"`で`"合計は8です"`という1つの文字列が完成する
- それを`println`で画面に表示する

C言語では文字列と数値を1つの`printf`でまとめて出すのに`printf("合計は%dです\n", sum);`のように**書式指定(`%d`)**を使いましたが、Javaでは`+`だけで文字列と数値をつなげられます。どちらも最終的にやっていることは同じ「文字列と数値を混ぜて1行表示する」ことです。

---

## `sc.close();`

```java
sc.close();
```

`Scanner`を作った直後は、エディタ上に次のような警告が出ます（エラーではなく警告です）。

> Resource leak: 'sc' is never closed

これは**図書館から本を借りるイメージ**で考えるとわかりやすいです。

- `new Scanner(System.in)` → 図書館から本を1冊借りてくる
- `sc.close()` → 借りた本を返却する

`Scanner`は「キーボード入力」という外部の資源(リソース)を借りて使っているので、使い終わったら`close()`で返してあげるのがマナーです。1回借りて返さなくても、その場では何も起きません。でも「借りる→返さない」を繰り返していたら、いずれ資源が足りなくなって不具合が起きることがあります。

今回のような一度実行して終わるだけの小さいプログラムでは、`close()`を書かなくてもプログラム終了時にOSが自動で片付けてくれるため実害はありませんが、**「`new`で何かを作ったら、使い終わったら`close()`で返す」という習慣**をつけておくと、この先ファイル操作や通信など「外部のものを借りる」処理を書くようになったときに事故を防げます。だからこのコードでも、`main`の最後に`sc.close();`を入れています。

---

## まとめ

```
import java.util.Scanner;                          ← java.utilパッケージからScannerを読み込む(Cの#include相当)
Scanner sc = new Scanner(System.in);                ← キーボード入力を読み取る道具(実体)を作る
System.out.print(...);                              ← 改行なしで表示する
int num1 = sc.nextInt();                            ← キーボードから整数を1つ読み取ってnum1に入れる
int sum = num1 + num2;                              ← 整数の足し算(Cと同じ)
System.out.println("合計は" + sum + "です");         ← 文字列と数値を+で連結して表示する
sc.close();                                          ← 借りたScannerを返却する
```

この課題で新しく登場したポイントは、**`import`によるパッケージの読み込み**、**`Scanner`を使ったキーボード入力の受け取り方**、**`+`による文字列連結**、そして**使い終わったリソースを`close()`で返す習慣**の4つです。`class`・`static`・`new`という土台の理解があれば、これらは「その上に載る具体的な使い方」として素直に理解できたと思います。
