sql: installment\ris.sql

A. Calculate a Saving Account
url: GET: http://localhost:8076/deposit/estimated
Param
1. String tenor
2. String firstDepositAmount,
3. String monthlyDepositAmount,
4. String purpose
5. 
 example respons: 27560000.00
 example respons Error: Response(status=ERROR, data=, message=Field TENOR harus angka)

            
	note: menurut saya monthlyDepositAmount dan firstDepositAmount harusnya sama 
	dan untuk  purpose tidak dibutuhkan untuk perhitungan.
  jadi saya ada url baru 
  url: GET http://localhost:8076/deposit/calculation
  Param
1. String tenor
2. String monthlyDepositAmount,
 example respons: 
 example respons: 27560000.00
	
B. Create Saving Account
Asumsi: setiap Create Saving Account harus mempunyain account bank terlebih dahulu
-Setiap 1 AcountBank dapat memiliki deposit lebih dari 1.
  url: POST http://localhost:8076/deposit
  param:
   1.String tenor,
   2. String firstDepositAmount,
   3. String monthlyDepositAmount,
   4. tring purpose,
   5. String requestBy,
   6. String accountId
   example respons success:
   Response(status=00, data=Deposit(id=402881e57bfea7e2017bfea84b0e0000, accountId=account1, tenor=12, tenorPaid=1, firstAmount=4000000, monthlyAmount=2000000, purpose=Education, status=1, createdBy=Admin1, createdDate=Sun Sep 19 22:24:07 ICT 2021, modifiedBy=null, modifiedDate=null), message=SUCCESS)
   
    example respons error:
    Response(status=ERROR, data=, message=Field TENOR tidak boleh kosong)

C.Get all savings
  url: GET http://localhost:8076/deposit
 param: none
 example respons: 
Response(status=00, data=[DepositUser(id=402881e57bfea7e2017bfea84b0e0000, name=Lina, accountId=account1, tenor=12, tenorPaid=1, firstAmount=4000000, monthlyAmount=2000000, purpose=Education, status=Active, createdBy=Admin1, createdDate=2021-09-19 00:00:00.0, modifiedBy=null, modifiedDate=null), DepositUser(id=402881e57bfea7e2017bfea84b0e0001, name=Lina, accountId=account1, tenor=12, tenorPaid=12, firstAmount=2000000, monthlyAmount=2000000, purpose=Education, status=Done, createdBy=Admin1, createdDate=2021-09-19 00:00:00.0, modifiedBy=Admin1, modifiedDate=2021-09-19 00:00:00.0), DepositUser(id=402881e57bfea7e2017bfea84b0e0002, name=nyx, accountId=account2, tenor=12, tenorPaid=3, firstAmount=3000000, monthlyAmount=3000000, purpose=Others, status=Inactive, createdBy=Admin1, createdDate=2021-09-19 00:00:00.0, modifiedBy=Admin1, modifiedDate=2021-09-19 00:00:00.0)], message=SUCCESS)



D.Get a saving details
 url: GET http://localhost:8076/deposit/{accountId}
 example respons: 
 Response(status=00, data=[DepositUser(id=402881e57bfea7e2017bfea84b0e0000, name=Lina, accountId=account1, tenor=12, tenorPaid=1, firstAmount=4000000, monthlyAmount=2000000, purpose=Education, status=1, createdBy=Admin1, createdDate=2021-09-19 00:00:00.0, modifiedBy=null, modifiedDate=null), DepositUser(id=402881e57bfea7e2017bfea84b0e0001, name=Lina, accountId=account1, tenor=12, tenorPaid=12, firstAmount=2000000, monthlyAmount=2000000, purpose=Education, status=3, createdBy=Admin1, createdDate=2021-09-19 00:00:00.0, modifiedBy=Admin1, modifiedDate=2021-09-19 00:00:00.0)], message=SUCCESS)

respondMessage= Error:
Response(status=00, data=[], message=Data deposit user account7 tidak ditemukan)


