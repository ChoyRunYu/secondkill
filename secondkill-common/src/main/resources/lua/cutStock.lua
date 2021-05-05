-- 扣减库存lua脚本
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

-- 获取库存的过期时间
local stockTtl = tonumber(redis.call('ttl', stockKey))
-- 获取库存
local stock = tonumber(redis.call('get', stockKey))
-- 获取用户是否参加过活动
local isBuy = tonumber(redis.call('SISMEMBER', msGoodsIsBuyKey, userIdValue))
-- 如果用户参加过
if isBuy == 1 then
    return "isBuy"
else
    -- 如果库存大于0，才能执行扣减库存操作
    -- 同时要将该用户参加过活动添加到redis中
    if stock > 0 then
        -- 扣减库存
        redis.call('del', stockKey)
        redis.call('set', stockKey, stock - 1)
        -- 设置过期时间
        redis.call('expire', stockKey, stockTtl)
        -- 将抢购过的用户添加到名单中
        redis.call('sadd', msGoodsIsBuyKey, userIdValue)
        -- 设置过期时间
        redis.call('expire', msGoodsIsBuyKey, stockTtl)
        return "true"
    else
        -- 如果库存等于0 则说明商品秒杀完
        return "end"
    end
end