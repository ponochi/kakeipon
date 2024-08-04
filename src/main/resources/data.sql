--[tbl_role]========================================================================================
INSERT INTO tbl_role(role_name) VALUES ('ADMIN');
INSERT INTO tbl_role(role_name) VALUES ('USER');

--[tbl_user]========================================================================================
INSERT INTO tbl_user(nick_name, last_name, first_name, password, email, birthday, phone_number, role_id, entry_date, update_date) values ('juiceA', 'Foo1', 'Bar1', 'test', 'juiceA@example.com', '1971-12-31 00:00:00', '00011112222', 1, '2024-07-19 00:00:00', NULL);
INSERT INTO tbl_user(nick_name, last_name, first_name, password, email, birthday, phone_number, role_id, entry_date, update_date) values ('juiceB', 'Foo2', 'Bar2', 'test', 'juiceB@example.com', '1970-01-01 00:00:00', '00011112222', 2, '2024-07-20 00:00:00', NULL);

--[tbl_first_class]=================================================================================
--[支出]===========================================================================================
INSERT INTO tbl_first_class(first_class_name) VALUES ('支出');
--[収入]===========================================================================================
INSERT INTO tbl_first_class(first_class_name) VALUES ('収入');
--[振替]===========================================================================================
INSERT INTO tbl_first_class(first_class_name) VALUES ('振替');

--[tbl_second_class]================================================================================
--[支出]===========================================================================================
--[支出] 食費 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '食費');
--[支出] 日用品 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '日用品');
--[支出] 交通費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '交通費');
--[支出] 医療費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '医療費');
--[支出] 保険料 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '保険料');
--[支出] 水道光熱費 -------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '水道光熱費');
--[支出] 通信費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '通信費');
--[支出] 教育費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '教育費');
--[支出] 交際費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '交際費');
--[支出] 娯楽費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '娯楽費');
--[支出] 大型出費 ---------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '大型出費');
--[支出] 年金 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, '年金');
--[支出] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (1, 'その他');
--[収入]===========================================================================================
--[収入] 給与・報酬 -------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (2, '給与・報酬');
--[収入] 賞与 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (2, '賞与');
--[収入] 副業 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (2, '副業');
--[収入] 年金 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (2, '年金');
--[収入] 臨時収入 ---------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (2, '臨時収入');
--[収入] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (2, 'その他');
--[振替]===========================================================================================
--[振替] 普通預貯金 -------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (3, '普通預貯金');
--[振替] 定期預貯金 -------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (3, '定期預貯金');
--[振替] 投資 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (3, '投資');
--[振替] 投機 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (3, '投機');
--[振替] ショッピング -----------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (3, 'ショッピング');
--[振替] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class(first_class_id, second_class_name) VALUES (3, 'その他');

--[tbl_second_class_by_order]=======================================================================
--[支出]===========================================================================================
--[支出] 食費 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (1, 1, 1, false);
--[支出] 日用品 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (2, 1, 2, false);
--[支出] 交通費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (3, 1, 3, false);
--[支出] 医療費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (4, 1, 4, false);
--[支出] 保険料 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (5, 1, 5, false);
--[支出] 水道光熱費 -------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (6, 1, 6, false);
--[支出] 通信費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (7, 1, 7, false);
--[支出] 教育費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (8, 1, 8, false);
--[支出] 交際費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (9, 1, 9, false);
--[支出] 娯楽費 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (10, 1, 10, false);
--[支出] 大型出費 ---------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (11, 1, 11, false);
--[支出] 年金 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (12, 1, 12, false);
--[支出] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (13, 1, 13, false);
--[収入]===========================================================================================
--[収入] 給与・報酬 -------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (14, 2, 1, false);
--[収入] 賞与 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (15, 2, 2, false);
--[収入] 副業 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (16, 2, 3, false);
--[収入] 年金 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (17, 2, 4, false);
--[収入] 臨時収入 ---------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (18, 2, 5, false);
--[収入] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (19, 2, 6, false);
--[振替]===========================================================================================
--[振替] 普通預貯金 -------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (20, 3, 1, false);
--[振替] 定期預貯金 -------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (21, 3, 2, false);
--[振替] 投資 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (22, 3, 4, false);
--[振替] 投機 -------------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (23, 3, 5, false);
--[振替] ショッピング -----------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (24, 3, 6, false);
--[振替] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_second_class_by_order(second_class_id, first_class_id, order_number, disabled) VALUES (25, 3, 7, false);

--[tbl_third_class]=================================================================================
--[支出]===========================================================================================
--[支出] 食費 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '食料品');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '飲料');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '酒類');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '菓子');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '外食');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '朝食');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '昼食');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '夕食');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '夜食');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, '間食');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (1, 1, 'その他');
--[支出] 日用品 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (2, 1, '消耗品');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (2, 1, '洗剤');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (2, 1, 'トイレットペーパー');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (2, 1, 'ティッシュペーパー');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (2, 1, 'キッチンペーパー');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (2, 1, '文房具');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (2, 1, 'その他');
--[支出] 交通費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (3, 1, '電車');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (3, 1, 'バス');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (3, 1, 'タクシー');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (3, 1, '飛行機');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (3, 1, '自動車 (ガソリン代等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (3, 1, '自転車 (駐輪代等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (3, 1, '徒歩 (通行料等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (3, 1, '交通系ICカード (チャージ等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (3, 1, 'その他');
--[支出] 医療費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (4, 1, '病院');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (4, 1, '薬');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (4, 1, '衛生用品');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (4, 1, '食品 (特定保健用食品等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (4, 1, '飲料 (特定保健用飲料等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (4, 1, 'その他');
--[支出] 保険料 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (5, 1, '国民健康保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (5, 1, '生命保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (5, 1, '医療保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (5, 1, '終身保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (5, 1, '自動車保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (5, 1, '火災保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (5, 1, '家財保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (5, 1, 'その他');
--[支出] 水道光熱費 -------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (6, 1, '電気');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (6, 1, 'ガス');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (6, 1, '水道');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (6, 1, 'その他');
--[支出] 通信費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, '携帯電話');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, '固定電話');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, '固定回線 (インターネット回線等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, 'インターネット (プロバイダ料金等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, 'オンラインサービス (クラウドサービス等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, 'サブスクリプション (動画配信サービス等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, 'テレビ (CATV・衛星放送料金等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, 'ラジオ (NHK受信料等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, '宅配便 (宅配料金等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, '郵便 (郵便料金等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (7, 1, 'その他');
--[支出] 教育費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (8, 1, '学費');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (8, 1, '塾代');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (8, 1, 'オンラインスクール代');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (8, 1, '教材費');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (8, 1, '新聞代');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (8, 1, '図書費 (参考書等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (8, 1, 'その他');
--[支出] 交際費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (9, 1, '友人');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (9, 1, '恋人');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (9, 1, '家族');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (9, 1, '同僚');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (9, 1, '上司');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (9, 1, '部下・後輩');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (9, 1, '取引先');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (9, 1, 'その他');
--[支出] 娯楽費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, '映画');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, '音楽');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, '演劇');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, '美術館');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, '博物館');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, '遊園地');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, 'スポーツ観戦');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, '国内旅行');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, '海外旅行');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, 'ゲーム');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (10, 1, 'その他');
--[支出] 大型出費 ---------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, '白物家電');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, 'パソコン関連');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, 'アプリ購入　(買い切り)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, 'アプリ購入　(サブスクリプション)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, 'デジタルガジェット (スマートフォン等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, 'その他家電');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, '家具');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, '什器 (照明器具等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, '家庭用品');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, '自動車 (新車購入等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, 'バイク (新車購入等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, '自転車 (新車購入等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, '冠婚葬祭 (結婚式費用等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (11, 1, 'その他');
--[支出] 年金 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (12, 1, '国民年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (12, 1, '厚生年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (12, 1, '共済年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (12, 1, 'その他');
--[支出] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (13, 1, '電子マネー　(チャージ等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (13, 1, '使途不明金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (13, 1, 'ATM手数料');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (13, 1, '銀行手数料');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (13, 1, '振込手数料');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (13, 1, '未分類');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (13, 1, 'その他');
--[収入]===========================================================================================
--[収入] 給与・報酬 -------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (14, 2, '給与');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (14, 2, '報酬');
--[収入] 賞与 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (15, 2, '夏期賞与');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (15, 2, '冬期賞与');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (15, 2, '期末賞与');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (15, 2, '臨時賞与');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (15, 2, 'その他');
--[収入] 副業 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (16, 2, '副業');
--[収入] 年金 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (17, 2, '国民年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (17, 2, '基礎年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (17, 2, '厚生年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (17, 2, '共済年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (17, 2, '老齢年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (17, 2, '障害基礎年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (17, 2, '障害厚生年金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (17, 2, 'その他');
--[収入] 臨時収入 ---------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, '医療保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, '生命保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, '終身保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, '終身保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, '自動車保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, '火災保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, '家財保険');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, 'キャッシュバック');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, 'キャンペーン');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (18, 2, 'その他');
--[収入] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (19, 2, 'ATM引出し');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (19, 2, 'その他');
--[振替]===========================================================================================
--[振替] 普通預貯金 -------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (20, 3, '普通預貯金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (20, 3, '定期預貯金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (20, 3, '貯蓄 (簿外・ヘソクリ等)');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (20, 3, '投資');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (20, 3, '投機');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (20, 3, 'ショッピング');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (20, 3, 'その他');
--[振替] 定期預貯金 -------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (21, 3, '普通預貯金');
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (21, 3, '貯蓄 (簿外・ヘソクリ等)');
--[振替] 投資 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (22, 3, '普通預貯金');
--[振替] 投機 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (23, 3, '普通預貯金');
--[振替] ショッピング -----------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (24, 3, '普通預貯金 (返金等)');
--[振替] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class(second_class_id, first_class_id, third_class_name) VALUES (25, 3, 'その他');


--[tbl_third_class_by_order]========================================================================
--[支出]===========================================================================================
--[支出] 食費 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (1, 1, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (2, 1, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (3, 1, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (4, 1, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (5, 1, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (6, 1, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (7, 1, 1, 7, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (8, 1, 1, 8, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (9, 1, 1, 9, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (10, 1, 1, 10, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (11, 1, 1, 11, false);
--[支出] 日用品 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (12, 2, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (13, 2, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (14, 2, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (15, 2, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (16, 2, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (17, 2, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (18, 2, 1, 7, false);
--[支出] 交通費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (19, 3, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (20, 3, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (21, 3, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (22, 3, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (23, 3, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (24, 3, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (25, 3, 1, 7, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (26, 3, 1, 8, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (27, 3, 1, 9, false);
--[支出] 医療費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (28, 4, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (29, 4, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (30, 4, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (31, 4, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (32, 4, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (33, 4, 1, 6, false);
--[支出] 保険料 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (34, 5, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (35, 5, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (36, 5, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (37, 5, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (38, 5, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (39, 5, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (40, 5, 1, 7, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (41, 5, 1, 8, false);
--[支出] 水道光熱費 -------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (42, 6, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (43, 6, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (44, 6, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (45, 6, 1, 4, false);
--[支出] 通信費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (46, 7, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (47, 7, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (48, 7, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (49, 7, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (50, 7, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (51, 7, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (52, 7, 1, 7, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (53, 7, 1, 8, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (54, 7, 1, 9, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (55, 7, 1, 10, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (56, 7, 1, 11, false);
--[支出] 教育費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (57, 8, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (58, 8, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (59, 8, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (60, 8, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (61, 8, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (62, 8, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (63, 8, 1, 7, false);
--[支出] 交際費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (64, 9, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (65, 9, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (66, 9, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (67, 9, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (68, 9, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (69, 9, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (70, 9, 1, 7, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (71, 9, 1, 8, false);
--[支出] 娯楽費 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (72, 10, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (73, 10, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (74, 10, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (75, 10, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (76, 10, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (77, 10, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (78, 10, 1, 7, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (79, 10, 1, 8, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (80, 10, 1, 9, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (81, 10, 1, 10, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (82, 10, 1, 11, false);
--[支出] 大型出費 ---------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (83, 11, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (84, 11, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (85, 11, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (86, 11, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (87, 11, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (88, 11, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (89, 11, 1, 7, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (90, 11, 1, 8, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (91, 11, 1, 9, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (92, 11, 1, 10, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (93, 11, 1, 11, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (94, 11, 1, 12, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (95, 11, 1, 13, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (96, 11, 1, 14, false);
--[支出] 年金 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (97, 12, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (98, 12, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (99, 12, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (100, 12, 1, 4, false);
--[支出] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (101, 13, 1, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (102, 13, 1, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (103, 13, 1, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (104, 13, 1, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (105, 13, 1, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (106, 13, 1, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (107, 13, 1, 7, false);
--[収入]===========================================================================================
--[収入] 給与・報酬 -------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (108, 14, 2, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (109, 14, 2, 2, false);
--[収入] 賞与 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (110, 15, 2, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (111, 15, 2, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (112, 15, 2, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (113, 15, 2, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (114, 15, 2, 5, false);
--[収入] 副業 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (115, 16, 2, 1, false);
--[収入] 年金 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (116, 17, 2, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (117, 17, 2, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (118, 17, 2, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (119, 17, 2, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (120, 17, 2, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (121, 17, 2, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (122, 17, 2, 7, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (123, 17, 2, 8, false);
--[収入] 臨時収入 ---------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (124, 18, 2, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (125, 18, 2, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (126, 18, 2, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (127, 18, 2, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (128, 18, 2, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (129, 18, 2, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (130, 18, 2, 7, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (131, 18, 2, 8, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (132, 18, 2, 9, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (133, 18, 2, 10, false);
--[収入] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (134, 19, 2, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (135, 19, 2, 2, false);
--[振替]===========================================================================================
--[振替] 普通預貯金 -------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (136, 20, 3, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (137, 20, 3, 2, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (138, 20, 3, 3, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (139, 20, 3, 4, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (140, 20, 3, 5, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (141, 20, 3, 6, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (142, 20, 3, 7, false);
--[振替] 定期預貯金 -------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (143, 21, 3, 1, false);
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (144, 21, 3, 2, false);
--[振替] 投資 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (145, 22, 3, 1, false);
--[振替] 投機 -------------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (146, 23, 3, 1, false);
--[振替] ショッピング -----------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (147, 24, 3, 1, false);
--[振替] その他 -----------------------------------------------------------------------------------
INSERT INTO tbl_third_class_by_order(third_class_id, second_class_id, first_class_id, order_number, disabled) VALUES (148, 25, 3, 1, false);
