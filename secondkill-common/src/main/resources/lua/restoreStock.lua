-- 用户未付款恢复库存
-- @auhtor choy
-- @date 2021/03/26


-- 秒杀活动id
local msGoodsId = KEYS[1]
-- 用户id
local userId = KEYS[2]

-- 秒杀活动库存key
local stockKey = "stock:"..msGoodsId
-- 是否参加过该活动key
local msGoodsIsBuyKey = "msGoods-"..msGoodsId
-- 用户id的值
local userIdValue = "userId-"..ARGV[1]

-- 获取库存
local stock = tonumber(redis.call('get', stockKey))
-- 获取库存的过期时间
local stockTtl = tonumber(redis.call('ttl', stockKey))

-- 回滚库存
redis.call('del', stockKey)
redis.call('set', stockKey, stock + 1)
-- 设置过期时间
redis.call('expire', stockKey, stockTtl)
-- 移出该用户已经参加过活动
redis.call('srem', msGoodsIsBuyKey, userIdValue)