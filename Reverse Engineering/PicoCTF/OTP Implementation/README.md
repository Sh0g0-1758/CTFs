# OTP Implementation

after decompiling in ghidra , I got 

```c++

undefined8 main(int param_1,undefined8 *param_2)

{
  byte bVar1;
  char cVar2;
  int iVar3;
  undefined8 uVar4;
  long in_FS_OFFSET;
  int local_f0;
  int local_ec;
  char local_e8 [100];
  undefined local_84;
  char local_78 [104];
  long local_10;
  
  local_10 = *(long *)(in_FS_OFFSET + 0x28);
  if (param_1 < 2) {
    printf("USAGE: %s [KEY]\n",*param_2);
    uVar4 = 1;
  }
  else {
    strncpy(local_e8,(char *)param_2[1],100);
    local_84 = 0;
    local_f0 = 0;
    while( true ) {
      iVar3 = valid_char((int)local_e8[local_f0]);
      if (iVar3 == 0) break;
      if (local_f0 == 0) {
        cVar2 = jumble((int)local_e8[0]);
        local_78[0] = cVar2 % '\x10';
      }
      else {
        cVar2 = jumble((int)local_e8[local_f0]);
        bVar1 = (byte)((int)cVar2 + (int)local_78[local_f0 + -1] >> 0x1f);
        local_78[local_f0] =
             ((char)((int)cVar2 + (int)local_78[local_f0 + -1]) + (bVar1 >> 4) & 0xf) - (bVar1 >> 4)
        ;
      }
      local_f0 = local_f0 + 1;
    }
    for (local_ec = 0; local_ec < local_f0; local_ec = local_ec + 1) {
      local_78[local_ec] = local_78[local_ec] + 'a';
    }
    if (local_f0 == 100) {
      iVar3 = strncmp(local_78,
                      "bajbgfapbcclgoejgpakmdilalpomfdlkngkhaljlcpkjgndlgmpdgmnmepfikanepopbapfkdgle ilhkfgilgabldofbcaedgfe"
                      ,100);
      if (iVar3 == 0) {
        puts("You got the key, congrats! Now xor it with the flag!");
        uVar4 = 0;
        goto LAB_001009ea;
      }
    }
    puts("Invalid key!");
    uVar4 = 1;
  }
LAB_001009ea:
  if (local_10 != *(long *)(in_FS_OFFSET + 0x28)) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return uVar4;
}

```

checking out jumble function, we get this : 


```c++
char jumble(char param_1)

{
  char local_c;
  
  local_c = param_1;
  if ('`' < param_1) {
    local_c = param_1 + '\t';
  }
  local_c = (local_c % '\x10') * '\x02';
  if ('\x0f' < local_c) {
    local_c = local_c + '\x01';
  }
  return local_c;
}
```

The important thing to notice is that the valid characters are limited to {0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f} ie. The Hex characters. So We can write a simple brute force attack to get the key, xor it with and flag.txt and finally decode this hex into utf-8 format to get our flag. 

After running solve.py, I got --> 

```
picoCTF{cust0m_jumbl3s_4r3nt_4_g0Od_1d3A_42dad069}
```