local account = 'account'
--获取from账号金额
local fromBalance = tonumber(redis.call('HGET', account, KEYS[1]))
--获取to账号金额
local toBalance = tonumber(redis.call('HGET', account, KEYS[2]))
--获取转账金额
local transAmount = tonumber(ARGV[1])
--如果from账号金额大于转账金额则进行转账
if fromBalance >= transAmount
then
    redis.call('HSET', account, KEYS[1], fromBalance - transAmount)
    redis.call('HSET', account, KEYS[2], toBalance + transAmount)
    return true
end
return false