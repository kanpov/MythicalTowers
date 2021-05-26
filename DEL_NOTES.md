# About
Explanations on awkward deletions.

## Delnote 1 - 26th May

I just yeet-ed the **ENTIRE TNT system** and the reason is terrible.  

So basically the system was:
1. TntEntity inheritor
2. TntBlock inheritor
3. TntEntityRenderUtility (it stays for now)
4. A bunch of dirty glue code mixins

It _was_ working until I tried to register the EntityType and for some unholy reason  
the generics didn't match and could not be cast.  

Now here's the dumb part (loud raging noises alert):  
**In EntityType class this generic stupidity DID COMPILE WITH ERRORS, and in my registry class IT DID NOT!**