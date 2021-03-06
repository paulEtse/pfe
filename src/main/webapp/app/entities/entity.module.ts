import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'team',
                loadChildren: './team/team.module#ChatbotTeamModule'
            },
            {
                path: 'channel',
                loadChildren: './channel/channel.module#ChatbotChannelModule'
            },
            {
                path: 'message',
                loadChildren: './message/message.module#ChatbotMessageModule'
            },
            {
                path: 'annotation',
                loadChildren: './annotation/annotation.module#ChatbotAnnotationModule'
            },
            {
                path: 'user-info',
                loadChildren: './user-info/user-info.module#ChatbotUserInfoModule'
            },
            {
                path: 'message',
                loadChildren: './message/message.module#ChatbotMessageModule'
            },
            {
                path: 'message',
                loadChildren: './message/message.module#ChatbotMessageModule'
            },
            {
                path: 'channel',
                loadChildren: './channel/channel.module#ChatbotChannelModule'
            },
            {
                path: 'source',
                loadChildren: './source/source.module#ChatbotSourceModule'
            },
            {
                path: 'source',
                loadChildren: './source/source.module#ChatbotSourceModule'
            },
            {
                path: 'channel',
                loadChildren: './channel/channel.module#ChatbotChannelModule'
            },
            {
                path: 'source',
                loadChildren: './source/source.module#ChatbotSourceModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ChatbotEntityModule {}
