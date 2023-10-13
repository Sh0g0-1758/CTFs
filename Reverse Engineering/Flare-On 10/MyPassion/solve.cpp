#include<bits/stdc++.h>
using namespace std;

int main() {
    auto address = 0x400c55;
    auto size = 0x147;
    auto fname = "dump0";
    auto file = fopen(fname, "wb");
    for (auto i=0; i<size; i++, address++)
    {
        auto x = DbgByte(address);
        fputc(x, file);
    }
    fclose(file);
}


