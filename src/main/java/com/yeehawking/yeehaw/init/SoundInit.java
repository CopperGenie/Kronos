package com.yeehawking.yeehaw.init;

import com.yeehawking.yeehaw.Yeehaw;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            Yeehaw.MOD_ID);

    public static final RegistryObject<SoundEvent> GENIE_DEATH = SOUNDS.register("entity.genie.death",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "entity.genie.death")));
    public static final RegistryObject<SoundEvent> GENIE_HURT = SOUNDS.register("entity.genie.hurt",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "entity.genie.hurt")));
    public static final RegistryObject<SoundEvent> LAMP_SUMMON = SOUNDS.register("lamp_summon",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "lamp_summon")));
    public static final RegistryObject<SoundEvent> LICH_SUMMON = SOUNDS.register("lich_summon",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "lich_summon")));
    public static final RegistryObject<SoundEvent> MIND_FLAYER_SUMMON = SOUNDS.register("mind_flayer_summon",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "mind_flayer_summon")));
    public static final RegistryObject<SoundEvent> TOAD_FATHER_SUMMON = SOUNDS.register("toad_father_summon",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "toad_father_summon")));
    public static final RegistryObject<SoundEvent> TREASURE_FAIRY_SUMMON = SOUNDS.register("treasure_fairy_summon",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "treasure_fairy_summon")));
    public static final RegistryObject<SoundEvent> HUMAN_DEATH = SOUNDS.register("entity.human.death",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "entity.human.death")));
    public static final RegistryObject<SoundEvent> HUMAN_HURT = SOUNDS.register("entity.human.hurt",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "entity.human.hurt")));
    public static final RegistryObject<SoundEvent> FB_AMBIENT = SOUNDS.register("fb_ambient",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "fb_ambient")));
    public static final RegistryObject<SoundEvent> FB_HURT = SOUNDS.register("fb_hurt",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "fb_hurt")));
    public static final RegistryObject<SoundEvent> FB_DEATH = SOUNDS.register("fb_death",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "fb_death")));
    public static final RegistryObject<SoundEvent> POLICE_BUZZ = SOUNDS.register("police_buzz",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "police_buzz")));
    public static final RegistryObject<SoundEvent> THE_POLICE_DEATH = SOUNDS.register("the_police_death",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "the_police_death")));
    public static final RegistryObject<SoundEvent> THE_POLICE_HURT = SOUNDS.register("the_police_hurt",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "the_police_hurt")));
    public static final RegistryObject<SoundEvent> X0026G_AMBIENT = SOUNDS.register("x0026g_ambient",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "x0026g_ambient")));
    public static final RegistryObject<SoundEvent> X0026G_DEATH = SOUNDS.register("x0026g_death",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "x0026g_death")));
    public static final RegistryObject<SoundEvent> X0026G_HURT = SOUNDS.register("x0026g_hurt",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "x0026g_hurt")));
    public static final RegistryObject<SoundEvent> KRORK_HURT = SOUNDS.register("krork_hurt",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "krork_hurt")));
    public static final RegistryObject<SoundEvent> KRORK_DIALOGUE_1 = SOUNDS.register("krork_dialogue_1",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "krork_dialogue_1")));
    public static final RegistryObject<SoundEvent> KRORK_DIALOGUE_2 = SOUNDS.register("krork_dialogue_2",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "krork_dialogue_2")));
    public static final RegistryObject<SoundEvent> KRORK_DIALOGUE_3 = SOUNDS.register("krork_dialogue_3",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "krork_dialogue_3")));
    public static final RegistryObject<SoundEvent> KRORK_DIALOGUE_4 = SOUNDS.register("krork_dialogue_4",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "krork_dialogue_4")));
    public static final RegistryObject<SoundEvent> KRORK_DIALOGUE_5 = SOUNDS.register("krork_dialogue_5",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "krork_dialogue_5")));
    public static final RegistryObject<SoundEvent> GASMAN_HURT = SOUNDS.register("gasman_hurt",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "gasman_hurt")));
    public static final RegistryObject<SoundEvent> GASMAN_DEATH = SOUNDS.register("gasman_death",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "gasman_death")));
    public static final RegistryObject<SoundEvent> FEMALE_HURT = SOUNDS.register("female_hurt",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "female_hurt")));
    public static final RegistryObject<SoundEvent> DESTROY_THE_CHILD = SOUNDS.register("destroy_the_child",
            () -> new SoundEvent(new ResourceLocation(Yeehaw.MOD_ID, "destroy_the_child")));


}
