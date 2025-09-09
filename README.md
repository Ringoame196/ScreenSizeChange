# ScreenSizeChange

特定のウィンドウのサイズを指定した大きさに変更したり、最大化との切り替え用のツールです。

## 概要
- 特定のウィンドウのサイズを指定して変更

## 動作環境
- Java 22
- OS: Windows（ウィンドウ操作のため）

## インストール
1. `ScreenSizeChange.jar` を任意のフォルダに保存
2. コマンドプロンプトやPowerShellから実行可能

## 使い方

### セットアップ
```bash
java -jar ScreenSizeChange.jar <windowName>
```
- 現在のウィンドウサイズと最大化状態を切り替える .bat ファイルを自動生成

### サイズ指定で実行
```bash
java -jar ScreenSizeChange.jar <windowName> <minimizeWidth> <minimizeHeight>
```