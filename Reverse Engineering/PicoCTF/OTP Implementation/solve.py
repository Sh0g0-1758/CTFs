char_set = '0123456789abcdef'
 
def jumble(ch):
    local_c = ord(ch)
    if ( 0x60 < local_c ):
        local_c = ord(ch) + 9
    local_c = (local_c % 0x10) * 0x02
    if ( 0xf < local_c ):
        local_c += 1
    return chr(local_c)
 
def valid_char(ch):
    return (ch in '0123456789abcdef')

str_2 = "bajbgfapbcclgoejgpakmdilalpomfdlkngkhaljlcpkjgndlgmpdgmnmepfikanepopbapfkdgleilhkfgilgabldofbcaedgfe"
key = [0] * 100
j = 0
 
str_1 = [0] * 100
 
while ( j < 100 ):
    str_1[j] = chr( ord(str_2[j]) - ord('a') )
    j += 1

i = 0
while (i < 100):
    if ( i==0 ):
        for c in char_set:
            jumble_res = jumble(c)
            res_1 = chr(ord(jumble_res) % 0x10)
            if str_1[0]==res_1:
                key[0] = c
                break
    else:
        for c in char_set:
            jumble_res = jumble(c)
            iv3 = ord(jumble_res) + ord(str_1[i-1])
            bv2 = (iv3 >> 0x1f)
            res_2 = chr( (ord(jumble_res) + ord(str_1[i-1]) + (bv2 >> 4) & 0xf ) - ( bv2 >> 4 ) )
            if str_1[i]==res_2:
                key[i] = c
                break
    i += 1
final_key = ''.join(key)
print(final_key)
ct = "ffadccb05b5892418ff068dd9d42231e8caf8ebb289ea1873f0a474cabe7ce598db77bac9dfef1d7c2b5af3c35bf5844c082"
ct = int(ct, 16)
final_key = int(final_key, 16)

flag = ct ^ final_key
flag = "7069636f4354467b63757374306d5f6a756d626c33735f3472336e745f345f67304f645f316433415f34326461643036397d"
# For some reason in python the last hex bit was missing , so used https://xor.pw/# to calculate xor of two hex numbers. 
# print(flag)
# print(flag[99])
flag = bytes.fromhex(flag).decode('utf-8')
print(flag)