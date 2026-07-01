package com.example.mapper;

/**
 * ドメインモデルのTestTodoを `APIモデル` に変換するマッパークラス
 *   - 値を異なる型の同じフィールドにコピーするだけ
 *   - わざわざインスタンス化する必要はない
 *    上記の理由から、
 *    - privateコンストラクタでインスタンス化を禁止
 *    - メソッドはstatic 
 *    と定義しています
 * 
 * また、他のクラスで継承するような処理ではないため、final class として定義しています
 */
public final class TestTodoMapper {

    // コンストラクタをprivateにしてインスタンス化を禁止
    // 省略すると publicなコンストラクタが作成されるので注意
    private TestTodoMapper() {}

    /**
     * ドメインモデルをAPI型に変換する
     * 
     * @param domainTestTodo ドメインモデルのTestTodo型
     * @return APIモデルのTestTodo型
     */
    public static com.example.generated.model.TestTodo toApiTestTodo(com.example.domain.TestTodo domainTestTodo) {

        // APIモデルの各フィールドに値を設定して返却するだけ
        com.example.generated.model.TestTodo apiTestTodo = new com.example.generated.model.TestTodo();        
        apiTestTodo.setTitle(domainTestTodo.getTitle());
        apiTestTodo.setDescription(domainTestTodo.getDescription());
        
        return apiTestTodo;
    }
}
